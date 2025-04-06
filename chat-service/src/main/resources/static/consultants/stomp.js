const stompClient = new StompJs.Client({
  brokerURL: 'ws://localhost:8080/stomp/chats'
});

stompClient.onConnect = (frame) => {
  setConnected(true);
  showChatRooms(0);
  stompClient.subscribe('/sub/chats/updates',
      (chatmessage) => {
        toggleNewMessageIcon(JSON.parse(chatmessage.body).id, true);
        updateMemberCount(JSON.parse(chatmessage.body));
      });
  console.log('Connected: ' + frame);
};

function toggleNewMessageIcon(chatroomId, toggle) {
  if(chatroomId === $("#chatroom-id").val()) return;
  if (toggle) {
    $("#new_" + chatroomId).show();
  }else {
    $("#new_" + chatroomId).hide();
  }
}

function updateMemberCount(chatRoom) {
  $("#memberCount_" + chatRoom.id).text(chatRoom.memberCount);
}

stompClient.onWebSocketError = (error) => {
  console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
  console.error('Broker reported error: ' + frame.headers['message']);
  console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
  $("#connect").prop("disabled", connected);
  $("#disconnect").prop("disabled", !connected);
  $("#create").prop("disabled", !connected);
}

function connect() {
  stompClient.activate();
}

function disconnect() {
  stompClient.deactivate();
  setConnected(false);
  console.log("Disconnected");
}

function sendMessage() {
  let chatroomId = $("#chatroom-id").val();
  stompClient.publish({
    destination: "/pub/chats/" + chatroomId,
    body: JSON.stringify(
        { 'message': $("#message").val() })
  });
  $("#message").val("")
}

function showMessage(chatMessage) {
  $("#messages").append(
      "<tr><td>" + chatMessage.sender + " : " + chatMessage.message
      + "</td></tr>");
}

function createChatRoom() {
  $.ajax({
    type: 'post',
    dataType: 'json',
    url: '/chats?title=' + $("#chatroom-title").val(),
    success: (data) => {
      console.log('data: ', data);
      showChatRooms(0);
      enterChatroom(data.id);
    },
    error: (req, status, error) => {
      console.log('req: ', req);
      console.log('error: ', error);
    }
  })
}

function showChatRooms(pageNumber) {
  $.ajax({
    type: 'GET',
    dataType: 'json',
    url: '/consultants/chats?sort=id,desc&size=10&page=' + pageNumber,
    success: (data) => {
      console.log('data: ', data);
      renderChatrooms(data);
    },
    error: (req, status, error) => {
      console.log('req: ', req);
      console.log('error: ', error);
    }
  })
}

function renderChatrooms(page) {
  let chatrooms = page.content
  $("#chatroom-list").html(""); // 기존 내용을 초기화
  chatrooms.forEach((chatRoom) => {
    $("#chatroom-list").append(
        "<tr onclick='joinChatroom(" + chatRoom.id + ")'><td>" // chatroom -> chatRoom 수정
        + chatRoom.id + "</td><td>"
        + chatRoom.title
        + "<img src='new.png' id='new_" + chatRoom.id + "' style='display: "
        + getDisplayValue(chatRoom.hasNewMessage)
        + "'/></td><td id='memberCount_" + chatRoom.id + "'>"
        + chatRoom.memberCount + "</td><td>"
        + chatRoom.createdAt + "</td></tr>"
    );
  });

  if(page.first) {
    $("#prev").prop("disabled", true);
  } else {
    $("#prev").prop("disabled", false).click(() => showChatRooms(page.number - 1));
  }
  if(page.last) {
    $("#next").prop("disabled", true);
  }else {
    $("#next").prop("disabled", false).click(() => showChatRooms(page.number + 1));
  }
}

function getDisplayValue(hasNewMessage) {
  return hasNewMessage ? "inline" : "none";
}

let subscription;

function enterChatroom(chatroomId, newMember) {
  $("#chatroom-id").val(chatroomId);
  $("#messages").html("");
  showMessages(chatroomId);
  $("#conversation").show();
  $("#send").prop("disabled", false);
  $("#leave").prop("disabled", false);
  toggleNewMessageIcon(chatroomId, false)

  if (subscription !== undefined) {
    subscription.unsubscribe();
  }

  subscription = stompClient.subscribe('/sub/chats/' + chatroomId,
      (chatMessage) => {
        showMessage(JSON.parse(chatMessage.body));
      });

  if (newMember || newMember === undefined) {
    stompClient.publish({
      destination: "/pub/chats/" + chatroomId,
      body: JSON.stringify(
          { 'message': "님이 방에 들어왔습니다." })
    });
  }
}

function showMessages(chatroomId) {
  $.ajax({
    type: 'GET',
    dataType: 'json',
    url: '/chats/' + chatroomId + '/messages',
    success: (data) => {
      console.log('data: ', data);
      data.forEach((chatMessage) => {
        showMessage(chatMessage);
      });
    },
    error: (req, status, error) => {
      console.log('req: ', req);
      console.log('error: ', error);
    }
  })
}

function joinChatroom(chatroomId) {
  let currentChatroomId = $("#chatroom-id").val();
  $.ajax({
    type: 'post',
    dataType: 'json',
    url: '/chats/' + chatroomId + getRequestParam(currentChatroomId),
    success: (data) => {
      console.log('data: ', data);
      enterChatroom(chatroomId, data);
    },
    error: (req, status, error) => {
      console.log('req: ', req);
      console.log('error: ', error);
    }
  })
}

function getRequestParam(currentChatroomId) {
  return currentChatroomId === "" ? "" : "?currentChatroomId=" + currentChatroomId;
}

function leaveChatRoom() {
  let chatroomId = $("#chatroom-id").val();
  $.ajax({
    type: 'delete',
    dataType: 'json',
    url: '/chats/' + chatroomId,
    success: (data) => {
      console.log('data: ', data);
      showChatRooms(0);
      exitChatroom(chatroomId);
    },
    error: (req, status, error) => {
      console.log('req: ', req);
      console.log('error: ', error);
    }
  })
}

function exitChatroom(chatroomId) {
  $("#chatroom-id").val("");
  $("#conversation").hide();
  $("#send").prop("disabled", true);
  $("#leave").prop("disabled", true);
}

$(function () {
  $("form").on('submit', (e) => e.preventDefault());
  $("#connect").click(() => connect());
  $("#disconnect").click(() => disconnect());
  $("#create").click(() => createChatRoom());
  $("#leave").click(() => leaveChatRoom());
  $("#send").click(() => sendMessage());
});

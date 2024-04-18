package org.example;

import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.Record;

public class FilterProcessor implements Processor {

	private ProcessorContext context;

	@Override
	public void init(ProcessorContext context) {
		this.context = context;
	}

	@Override
	public void process(Record record) {
		String value = (String) record.value();
		if (value.length() > 5) {
			context.forward(record);
		}
	}

	@Override
	public void close() {
	}

}

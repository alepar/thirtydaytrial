package ru.alepar.gwt.tdt;

import com.google.gwt.event.shared.EventHandler;
import org.junit.Test;
import ru.alepar.gwt.tdt.client.history.HistoryEvent;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * User: alepar
 * Date: Jul 15, 2010
 * Time: 10:27:58 PM
 */
public class HistoryEventTest {

    private HistoryEvent<?> historyEvent = new TestHistoryEvent();

    @Test
    public void testParameterlessTokenMarshall() {
        assertThat(historyEvent.toString(), equalTo(historyEvent.label()));
    }

    @Test
    public void testParameterlessTokenUnmarshall() {
        assertThat(historyEvent.getKeys().isEmpty(), equalTo(true));
    }

    @Test
    public void testValuelessParamMarshall() {
        historyEvent.setParam("key", null);
        assertThat(historyEvent.toString(), equalTo("token;key"));
    }

    @Test
    public void testValuelessParamUnmarshall() {
        historyEvent = new TestHistoryEvent("token;key");
        assertThat(historyEvent.getKeys().size(), equalTo(1));
        assertThat(historyEvent.getKeys(), hasItem("key"));
        assertThat(historyEvent.getValue("key"), equalTo(null));
    }

    @Test
    public void testTwoParamsMarshall() {
        historyEvent.setParam("key1", "val1");
        historyEvent.setParam("key2", "val2");
        assertThat(historyEvent.toString(), equalTo("token;key1=val1,key2=val2"));
    }

    @Test
    public void testTwoParamsUnmarshall() {
        historyEvent = new TestHistoryEvent("token;key1=val1,key2=val2");
        assertThat(historyEvent.getKeys().size(), equalTo(2));
        assertThat(historyEvent.getKeys(), allOf(hasItem("key1"), hasItem("key2")));
        assertThat(historyEvent.getValue("key1"), equalTo("val1"));
        assertThat(historyEvent.getValue("key2"), equalTo("val2"));
    }

    private static class TestHistoryEvent extends HistoryEvent {
        private TestHistoryEvent() {
        }

        private TestHistoryEvent(String historyToken) {
            super(historyToken);
        }

        @Override
        public String label() {
            return "token";
        }

        @Override
        public Type getAssociatedType() {
            throw new RuntimeException("Not Implemented");
        }

        @Override
        protected void dispatch(EventHandler eventHandler) {
            throw new RuntimeException("Not Implemented");
        }
    }
}

package ru.alepar.gwt.tdt.client.history;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * User: alepar
 * Date: Jul 15, 2010
 * Time: 9:44:01 PM
 */
public abstract class HistoryEvent<H extends EventHandler> extends GwtEvent<H> {

    private static final String TOKEN_SEPARATOR = ";";
    private static final String PAIR_SEPARATOR = ",";
    private static final String KEYVALUE_SEPARATOR = "=";
    
    protected LinkedHashMap<String, String> parameterMap = new LinkedHashMap<String, String>();

    public HistoryEvent() {
    }

    public HistoryEvent(String historyToken) {
        String[] breakout = historyToken.split(TOKEN_SEPARATOR, 2);
        if(!breakout[0].equals(label())) {
            throw new IllegalArgumentException("passed ivalid token " + breakout[0] + " to event type " + this.getClass().getName());
        }
        if(breakout.length == 2) {
            String paramString = breakout[1];
            String[] params = paramString.split(PAIR_SEPARATOR);
            for (String param : params) {
                String[] paramBreakout = param.split(KEYVALUE_SEPARATOR, 2);
                if(paramBreakout.length == 1) {
                    parameterMap.put(paramBreakout[0], null);
                } else {
                    parameterMap.put(paramBreakout[0], paramBreakout[1]);
                }
            }
        }
    }

    @Override
    public String toString() {
        String result = label();
        if (parameterMap.size() > 0) {
            result += TOKEN_SEPARATOR;
            for (String key : parameterMap.keySet()) {
                String value = parameterMap.get(key);
                result += key;
                if(value != null) {
                    result += KEYVALUE_SEPARATOR + value;
                }
                result += PAIR_SEPARATOR;
            }
            result = result.substring(0, result.length()-1);
        }
        return result;
    }

    public Collection<String> getKeys() {
        return parameterMap.keySet();
    }

    public String getValue(String key) {
        return parameterMap.get(key);
    }

    public void setParam(String key, String value) {
        parameterMap.put(key, value);
    }

    public abstract String label();
}

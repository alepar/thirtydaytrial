package ru.alepar.gwt.tdt.client.history;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * User: alepar
 * Date: Jul 15, 2010
 * Time: 9:44:01 PM
 */
public abstract class HistoryEvent<H extends EventHandler> extends GwtEvent<H> {

    protected LinkedHashMap<String, String> parameterMap = new LinkedHashMap<String, String>();

    public HistoryEvent() {
    }

    public HistoryEvent(String historyToken) {
        String[] breakout = historyToken.split(";", 2);
        if(breakout.length == 2) {
            String paramString = breakout[1];
            String[] params = paramString.split(",");
            for (String param : params) {
                String[] paramBreakout = param.split("=", 2);
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
            result += ";";
            for (String key : parameterMap.keySet()) {
                String value = parameterMap.get(key);
                result += key;
                if(value != null) {
                    result += "=" + value;
                }
                result += ",";
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

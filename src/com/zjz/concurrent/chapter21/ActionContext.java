package com.zjz.concurrent.chapter21;

import javax.naming.Context;
import javax.security.auth.login.Configuration;

public class ActionContext {
    private static final ThreadLocal<Context> context = ThreadLocal.withInitial(Context::new);

    public static Context get() {
        return context.get();
    }
    static class Context{
        private Configuration configuration;

        public Configuration getConfiguration() {
            return configuration;
        }

        public void setConfiguration(Configuration configuration) {
            this.configuration = configuration;
        }
    }
}

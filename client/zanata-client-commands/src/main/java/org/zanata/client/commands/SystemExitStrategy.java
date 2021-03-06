package org.zanata.client.commands;

@edu.umd.cs.findbugs.annotations.SuppressFBWarnings({ "DM_EXIT" })
public class SystemExitStrategy implements AppAbortStrategy {
    @Override
    public void abort(String msg) {
        System.exit(1);
    }

    @Override
    public void abort(Throwable e) {
        System.exit(1);
    }
}

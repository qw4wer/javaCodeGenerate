package tk.qw4wer.codeGenerate.utils.logs;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class TextAreaAppender extends AppenderSkeleton {
    @Override
    protected void append(LoggingEvent event) {
        String format = this.getLayout().format(event);
        LogUtils.log2Area(format);
    }

    @Override
    public void close() {
        this.closed = true;
    }

    @Override
    public boolean requiresLayout() {
        return false;
    }

}

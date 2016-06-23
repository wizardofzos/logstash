package org.logstash.ackedqueue;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Queue implements Closeable {

    private PageHandler ph;

    public Queue(String dirPath, int pageSize) {
        this.ph = new PageHandler(dirPath, pageSize);
    }

    public void open() throws FileNotFoundException {
        this.ph.open();
    }

    // add data to the queue
    // @param data to be pushed data
    public synchronized void push(byte[] data)
    {
        this.ph.write(data);
    }

    public synchronized Element use()
    {
        return this.ph.read();
    }

    public synchronized List<Element> use(int batchSize)
    {
        return this.ph.read(batchSize);
    }

    public synchronized void ack(Element e)
    {
        this.ack(e);
    }

    public synchronized void ack(List<Element> batch)
    {
        this.ack(batch);
    }


    public void purge() throws IOException
    {
        // TBD
    }

    public void clear() throws IOException
    {
        // TBD
    }

    @Override
    public void close() throws IOException {
        // TBD
    }
}
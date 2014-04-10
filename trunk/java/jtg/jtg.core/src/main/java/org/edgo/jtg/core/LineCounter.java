package org.edgo.jtg.core;

public class LineCounter {

    private int lineNumb = 0;

    public LineCounter()
    {
    }

    public LineCounter(LineCounter value)
    {
        lineNumb = value.lineNumb;
    }

    public LineCounter(int value)
    {
        lineNumb = value;
    }

    public LineCounter increment()
    {
        lineNumb++;
        return this;
    }

    public LineCounter add(int value)
    {
        lineNumb += value;
        return this;
    }

    public void set(int value)
    {
        lineNumb = value;
    }

    public int value()
    {
        return lineNumb;
    }

}

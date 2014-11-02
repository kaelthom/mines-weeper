// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Highscore.java

package datas;


public class Highscore implements Comparable<Highscore>
{

    private Long id;
    private String name;
    private String date;
    private long score;
	private int percent;

	public Highscore(String name, String date, long score, int percent)
    {
        this.date = date;
        this.name = name;
        this.score = score;
        this.percent = percent;
    }

    public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public long getScore()
    {
        return score;
    }

    public void setScore(long score)
    {
        this.score = score;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

	@Override
	public int compareTo(Highscore highscore) {
		if (this.percent<highscore.percent) {
			return 1;
		} else if (this.percent>highscore.percent) {
			return -1;
		} else {
			if (this.score<highscore.score) {
				return -1;
			} else if (this.score>highscore.score) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}

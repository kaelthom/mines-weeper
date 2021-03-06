// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Highscore.java

package datas;

import tools.parsers.csv.CsvColumn;

import java.util.Objects;

public class Highscore implements Comparable<Highscore> {

    // fields are public for reflective treatment in CsvParser
    @CsvColumn(column = 0)
    public String name;
    @CsvColumn(column = 2)
    public String date;
    @CsvColumn(column = 4)
    public long score;
    @CsvColumn(column = 3)
    public int percent;
    @CsvColumn(column = 1)
    public Long id;

    public Highscore() {

    }

    public Highscore(long id, String name, String date, long score, int percent) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Highscore highscore = (Highscore) o;
        return score == highscore.score &&
                percent == highscore.percent &&
                Objects.equals(id, highscore.id) &&
                Objects.equals(name, highscore.name) &&
                Objects.equals(date, highscore.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, score, percent);
    }

    @Override
    public int compareTo(Highscore highscore) {
        if (this.percent < highscore.percent) {
            return 1;
        } else if (this.percent > highscore.percent) {
            return -1;
        } else {
            return Long.compare(this.score, highscore.score);
        }
    }
}

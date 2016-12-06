package com.example.aidlserver;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 客户端的Person也必须要是跟服务端的包名一样
 * Created by Administrator on 2016/12/6.
 * implemtns Parcelable实现序列化的目的：就是不断的拆包（把大数据打碎成为小数据）
 * 要想利用AIDL传递Person类，就需要实现Parcelable
 */

public class Person implements Parcelable{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**这个构造函数比较特别，是下面createFromParcel中为了从Parcel取出数据而写的构造方法*/
    public Person(Parcel parcel) {
        // 这个顺序一定要一致
        this.name = parcel.readString();
        this.age = parcel.readInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /** 把数据Person里面的字段写入一个Parcel里面*/
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(age);
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        /** 把数据从Parcel里面取出来 */
        @Override
        public Person createFromParcel(Parcel parcel) {
            return new Person(parcel);//构造函数被写在上面了。
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[0];
        }
    };

    /** 这么做得目的，只是为了让我们在打印消息的时候，直接用toString方法，把名字和年龄都打印出来。比较方便*/
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

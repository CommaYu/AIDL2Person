// IImoocAidl.aidl
package com.example.aidlserver;

// Declare any non-default types here with import statements
import com.example.aidlserver.Person;

interface IImoocAidl {
    // 一个返回类型是List<Person>的方法，用in没解释清楚，一般说是用in
    List<Person> add(in Person peron);
}

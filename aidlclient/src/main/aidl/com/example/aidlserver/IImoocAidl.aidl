// IImoocAidl.aidl
package com.example.aidlserver;

// Declare any non-default types here with import statements
import com.example.aidlserver.Person;

interface IImoocAidl {
    List<Person> add(in Person peron);
}

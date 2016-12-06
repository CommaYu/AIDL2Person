# AIDL2Person
客户端把Person类的名字和年龄，远程写入到服务端里面。通过AIDL传递大数据的实例（把Person实体类，从客户端调用服务端方法，添加Person里面的名字和年龄）
#学习到
1.Person类implements Parcelable的原因：因为Parcelable属于AIDL可以传递数据类型，AIDL不能直接传递Person类，所以
需要实现Parcelable方便被AIDL传递
2.Person类中的toSting方法，利用alt+ins自动插入的一个方法，目的只是为了在打印logd的时候，直接输出Person中的名字和年龄
3.在IImoocAidl.aidl中如果我们不为Person导入包，AS是不会为我们自动导包的。如果我们不导入包，会出错的。

#注意点
1.Person中的一个特别的构造函数怎么来的？看看aidlserver的源代码里面有说public Person(Parcel parcel)
2.aidl中的Person.aidl是强制写出来的
3.Person所在的包名，客户端和服务端中一定要一样
4.aidl在客户端和服务端，文件名字和包名都要一样。


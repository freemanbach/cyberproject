using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using System.text;
using System.Linq;

namespace MethodHiding{
    class program{
        statci void Main(string[] args){
            SomeBaseClass SomeBaseClass1 = new SomeBaseClass();
            SomeBaseClass1.Method1();
            SomeBaseClass1.Method2();

            SomeBaseClass SomeBaseClass2 = new SomeDerivedClass();
            SomeBaseClass2.Method1();
            SomeBaseClass2.Method2();

            SomeDerivedClass SomeBaseClass3 = new SomeDerivedClass();
            SomeBaseClass3.Method1();
            SomeBaseClass3.Method2();}
    }
}

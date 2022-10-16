using System.Threading.Tasks;
using System.text;
using System.Linq;
using System;

namespace MethodHiding{
    class SomeBaseClass{
        public void Method1()
        {
            console.WriteLine("Password is Anita");
        }
        public virtual void Method2() 
        {
            console.WriteLine("");
        }
    }
}
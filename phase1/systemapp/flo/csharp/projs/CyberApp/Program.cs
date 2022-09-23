using System;
using System.Text;

namespace CyberApp {

    public class Family {

        public string john() {
            string a = "https://";
            return a;
        }
        public string mary() {
            string b = "sites.";
            return b;
        }
        public string bob() {
            string c = "radford.";
            return c;
        }
        public string chris() {
            string d = "edu/";
            return d;
        }
        public string lewis() {
            string e = "~flo/";
            return e;
        }
        public string andrew() {
            string f = "index";
            return f;
        }
        public string gretel() {
            string g = ".php";
            return g;
        }
    }
  
    public class Program {
        public static void Main(string[] args) {
            Family abc = new Family();
            string ans = "";
            ans = abc.john() + abc.mary() + abc.bob() + abc.chris() + abc.lewis() + abc.andrew() + abc.gretel();
        }
    }
}

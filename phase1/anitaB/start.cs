using System;
using System.Text;

namespace CyberApp {

    public class findMe {

        public string john() {
            string a = "https://sites.radford.edu/~flo/index.php";
            return a;
        }
        
    }
  
    public class Program {
        public static void Main(string[] args) {
            Family abc = new Family();
            string ans = "";
            ans = abc.john();
        }
    }
}

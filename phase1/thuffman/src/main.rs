use std::fmt::{Display, Formatter, Result};

fn main() {
    let cake = CheeseCake {
        one: seven(),
        two: six(),
        three: five(),
        four: four(),
        five: three(),
        six: two(),
        seven: one(),
    };
    let _ans: String = cake.to_string();
    //println!("{}", cake.to_string());
}

struct CheeseCake {
    seven: String,
    six: String,
    five: String,
    four: String,
    three: String,
    two: String,
    one: String,
}

impl Display for CheeseCake {
    fn fmt(&self, f: &mut Formatter) -> Result {
        write!(
            f,
            "{}{}{}{}{}{}{}",
            self.seven, self.six, self.five, self.four, self.three, self.two, self.one
        )
    }
}

fn one() -> String {
    String::from("https://")
}

fn two() -> String {
    String::from("sites.")
}

fn three() -> String {
    String::from("radford.")
}

fn four() -> String {
    String::from("edu/")
}

fn five() -> String {
    String::from("~flo/")
}

fn six() -> String {
    String::from("index.")
}

fn seven() -> String {
    String::from("php")
}

<?php
session_start();
?>
<!doctype html>
<html lang="en">
    <link rel="stylesheet" href="style.css" type="text/css" media="all" />

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Crack this code?></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
</head>

<body>
    <div>
        <h2 style = "color:blue;">Enter three decrypted words</h2>
    </div>
    <div>
        <br><br>
        <p style = "color:red";>Here, you are provided with three encrypted strings using AES 256 CBC approach with a random seed of 32, you
            will provided the decrypted values as such: </p>
        <p style = "color:blue;"><?php echo "1) ".$_SESSION['eans1']; ?></p>
        <p style = "color:blue;"><?php echo "2) ".$_SESSION['eans2']; ?></p>
        <p style = "color:blue;"><?php echo "3) ". $_SESSION['eans3']; ?></p>
    </div>

    <div>
        <?php

            $cnt = 1;
            function encrypt(array $messages, $method, $key, $iv) : array {
                $em = array();
                for($i = 0; $i < count($messages); $i++) {
                    $tmp = openssl_encrypt($messages[$i], $method,$key,0,$iv);
                    array_push($em, $tmp);
                }
                return $em;
            }

            function decrypt(array $messages, $method, $key, $iv) : array {
                $dm = array();
                for($i = 0; $i < count($messages); $i++) {
                    $tmp = openssl_decrypt($messages[$i],$method,$key,0,$iv);
                    array_push($dm, $tmp);
                }
                return $dm;
            }

            $m = array();
            $m1 = base64_decode("ZnJvZG8=");
            $m2 = base64_decode("c2F1cm9u");
            $m3 = base64_decode("pngvQG8=");
            array_push($m, $m1, $m2,$m3);

            $method = "aes-256-cbc";
            $key = openssl_random_pseudo_bytes(32);
            $iv = openssl_random_pseudo_bytes(openssl_cipher_iv_length($method));

            $encmess = encrypt($m,$method,$key,$iv);
            $_SESSION['eans1'] = $encmess[0];
            $_SESSION['eans2'] = $encmess[1];
            $_SESSION['eans3'] = $encmess[2];

            function correct($url) {
                header('Location: '. $url);
                die();
             }

            function incorrect($url) {
                header('Location: ' . $url);
                die();
            }

            if ( isset($_POST['key1']) && isset($_POST['key2']) && isset($_POST['key3']) ) {
                    $decmess = decrypt($encmess, $method, $key,$iv);
                    $_SESSION['dans1'] = $decmess[0];
                    $_SESSION['dans2'] = $decmess[1];
                    $_SESSION['dans2'] = $decmess[2];


               if ( trim($_POST['key1']) == $_SESSION['dans1'] && trim($_POST['key2']) == $_SESSION['dans2'] && trim($_POST['key3']) == $_SESSION['dans3'] ) {
                    $_SESSION["user"] = "player";
                    $_SESSION["count"] = $cnt++;
                    correct("yes.php");
               } else {
                   $_SESSION["user"] = "player";
                   $_SESSION["count"] = $cnt++;
                   incorrect("no.php");
               }
            }
         ?>
    </div>

    <form action="<?php echo $_SERVER['PHP_SELF']; ?>" method="post">
            <h2 style = "color: red;">Type the encrypted key answers below for each decrypted values</h2>
        <div class="form-row">
            <div class="form-group col-sm-4">
                <label style = "color:blue" for="key1">Key Value 1</label>
                <input type="text" class="form-control" id="key1" name="key1" placeholder="Enter Answer" maxlength="10"
                    size="10" required autofocus />
            </div>
            <div class="form-row">
                <div class="form-group col-sm-4">
                    <label style = "color:blue" for="key2">Key Value 2</label>
                    <input type="text" class="form-control" id="key2" name="key2" placeholder="Enter Answer"
                        maxlength="10" size="10" required autofocus />
                </div>
                <div class="form-row">
                    <div class="form-group col-sm-4">
                        <label style = "color:blue" for="key3">Key Value 3</label>
                        <input type="text" class="form-control" id="key3" name="key3" placeholder="Enter Answer"
                            maxlength="10" size="10" required />
                    </div>
                </div>

                <button style = "color:purple;" type="submit" name="submit">Submit</button>
    </form>
</body>

</html>
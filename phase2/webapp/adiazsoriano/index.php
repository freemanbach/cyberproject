<?php
session_start();
?>
<html lang="en">
   <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
      <title>Crack this code</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
        <link rel="stylesheet" href="main.css">
   </head>

   <body>
        <div class="mainbox">
            <div>
                <h2>Enter two decrypted words</h2> 
            </div>
            <div>
                <br><br>
                <p>Here, you are provided with two encrypted strings using AES 256 CBC approach with a random seed of 32, you will provided the decrypted values as such: </p>
                <p><?php echo $_SESSION['eans1']; ?></p>
                <p><?php echo $_SESSION['eans2']; ?></p>
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
                $m1 = base64_decode("Y2lyY3VpdA==");
                $m2 = base64_decode("bG9naWM=");
                array_push($m, $m1, $m2);

                $method = "aes-256-cbc";
                $key = openssl_random_pseudo_bytes(32);
                $iv = openssl_random_pseudo_bytes(openssl_cipher_iv_length($method));

                $encmess = encrypt($m,$method,$key,$iv);
                $_SESSION['eans1'] = $encmess[0];
                $_SESSION['eans2'] = $encmess[1];

                function correct($url) {
                    header('Location: '. $url);
                    die();
                }

                function incorrect($url) {
                    header('Location: ' . $url);
                    die();
                }

                if ( isset($_POST['key1']) && isset($_POST['key2']) ) {
                        $decmess = decrypt($encmess, $method, $key,$iv);
                        $_SESSION['dans1'] = $decmess[0];
                        $_SESSION['dans2'] = $decmess[1];

                if ( trim($_POST['key1']) == $_SESSION['dans1'] && trim($_POST['key2']) == $_SESSION['dans2'] ) {
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

                <div class="form-row">
                    <div class="form-group col-sm-4">
                        <label for="key1">Key Value 1</label>
                        <input type="text" class="form-control" id="key1" name="key1" placeholder="Enter Answer" maxlength="10" size="10" required autofocus />
                    </div>
                    <div class="form-group col-sm-4">
                        <label for="key2">Key Value 2</label>
                        <input type="text" class="form-control" id="key2" name="key2" placeholder="Enter Answer" maxlength="10" size="10" required />
                    </div>
                </div>
                <button type="submit" name="submit">Submit</button>
            </form>
        </div>
   </body>
</html>

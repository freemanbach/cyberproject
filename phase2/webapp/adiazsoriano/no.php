<?php

    session_start();

    if (isset($_SESSION['user'])) {
        echo "Incorrect !, Not making it to the next Phase! ";
        echo "You've Attempted the number of times: " . strval($_SESSION["count"]);
    }
?>

<html>
    <head>
    <meta charset="utf-8">
    <title>Cracking encrypted words</title>
    </head>
<body>
<br><br>

Click here to go back to the guessing board <a href="index.php" title="return">Return To Guessing Page</a>
<hr><br>
Click here to remove session <a href="logout.php" title="Logout">Session</a>
</body>
</html>

<?php

   session_start();
   unset($_SESSION["user"]);
   unset($_SESSION["count"]);
   
   echo 'Session has ended !';
   header('Refresh: 2; URL = index.php');

?>

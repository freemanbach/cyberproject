<?php
$encodeOrDecode = readLine("Encode(E) or Decode(D)?: ");
if($encodeOrDecode == "E"){
    $input = readLine("Enter plaintext: ");
    $num = readLine("Enter number to shift: ") % 26;
    echo Encode($input, $num) . "\n";
} else if($encodeOrDecode == "D"){
    $input = readLine("Enter plaintext: ");
    $num = readLine("Enter number to shift: ") % 26;
    echo Decode($input, $num) . "\n";
} else {
    echo "Incorrect Option";
}

function Cipher($char, $key)
{
    if (!ctype_alpha($char))
        return $char;

    $off = ord(ctype_upper($char) ? 'A' : 'a');
    return chr(fmod(((ord($char) + $key) - $off), 26) + $off);
}

function Encode($input, $key)
{
    $output = "";

    $inputArr = str_split($input);
    foreach ($inputArr as $ch)
        $output .= Cipher($ch, $key);

    return $output;
}

function Decode($input, $key)
{
    return Encode($input, 26 - $key);
}
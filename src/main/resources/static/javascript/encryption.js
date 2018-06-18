function encrypt(message, key) {
    var key_256 = key.split('');
    var text = document.getElementById("messageInput").value;
    var textBytes = aesjs.utils.utf8.toBytes(text);
    var aesCtr = new aesjs.ModeOfOperation.ctr(key, new aesjs.Counter(5));
    var encryptedBytes = aesCtr.encrypt(textBytes);
    console.log(encryptedBytes);
	
}
window.addEventListener("load", function() {
    var btnPrint = document.getElementById("btn-print");
    btnPrint.onclick = function () {
      var x, y;
      x = prompt("x값을 입력하세요.", 0);
      y = prompt("x값을 입력하세요.", 0);
      x = parseInt(x);
      y = parseInt(y);
      btnPrint.value = x + y;
    };
  }
);

 
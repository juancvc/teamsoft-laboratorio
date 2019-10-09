var operandoa;
var operandob;
var operacion;





function limpiar(){
	  resultado.textContent = "";
	}
	function resetear(){
	  resultado.textContent = "";
	  operandoa = 0;
	  operandob = 0;
	  operacion = "";
	}
	
	
	function resolver(){
  var res = 0;
  switch(operacion){
    case "+":
      res = parseFloat(operandoa) + parseFloat(operandob);
      break;
    case "-":
        res = parseFloat(operandoa) - parseFloat(operandob);
        break;
    case "*":
      res = parseFloat(operandoa) * parseFloat(operandob);
      break;
    case "/":
      res = parseFloat(operandoa) / parseFloat(operandob);
      break;
  }
  resetear();
  resultado.textContent = res;
}
	
	$('#buscaFormula').on('show.bs.modal', function () {
		init();
		})



/**
 * confirmar a exclusão
 */

function confirmar(id){
	let resposta = confirm("Tem certeza que deseja apagar o contato?")
	if(resposta === true){
		window.location.href="deletar?id="+id;
	}
}
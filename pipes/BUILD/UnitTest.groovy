// Funciona de arranque del proceso de compilacion
// devuelve 0 si OK  y <>0 si ERROR
def start() {
	log.info "PRUEBAS UNITARIAS"
	log.info "SCRIPT EVALUACION DE SONAR"

	junit allowEmptyResults: true, healthScaleFactor: 1.0, keepLongStdio: true, testResults: "**/build/test-results/test/*.xml"
	
	
	return 0; // devolveremos como error la fase de compilacion para que no continue el pipe
}

return this;

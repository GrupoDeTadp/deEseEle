package tadp.injector.dsl;

import static org.junit.Assert.*
import static tadp.injector.dsl.DSLDefinition.*

import org.junit.Test

import tadp.dependency.injector.fixture.velador.Alumbrable;
import tadp.dependency.injector.fixture.velador.Enchufable;
import tadp.dependency.injector.fixture.velador.EnchufeTresPatas;
import tadp.dependency.injector.fixture.velador.Lamparitas
import tadp.dependency.injector.fixture.velador.LamparitasOsram
import tadp.dependency.injector.fixture.velador.Velador

class DSLDefinitionTest {

	@Test
	void "Armate un velador con Lamparita Osram"(){
			
		def bean = motorDeInyeccion {
			usando LamparitasOsram.class como Alumbrable.class
			usando EnchufeTresPatas.class como Enchufable.class
			usando Velador.class como Velador.class
			dame Velador.class
		}
		
		//motor.armateUn "Velador" usando { una "LamparitaOsram" como "Lamaprita"}
	}
	
}
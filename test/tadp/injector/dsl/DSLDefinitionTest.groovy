package tadp.injector.dsl;

import static org.junit.Assert.*
import static tadp.injector.dsl.DSLDefinition.*
import junit.framework.Assert;

import org.junit.Test

import tadp.dependency.injector.fixture.velador.Alumbrable;
import tadp.dependency.injector.fixture.velador.Enchufable;
import tadp.dependency.injector.fixture.velador.EnchufeTresPatas;
import tadp.dependency.injector.fixture.velador.Lamparitas
import tadp.dependency.injector.fixture.velador.LamparitasOsram
import tadp.dependency.injector.fixture.velador.Velador

class DSLDefinitionTest {

	@Test
	void "armate un velador con Lamparita Osram"(){
			
		def bean = motorDeInyeccion({
			usando LamparitasOsram.class como Alumbrable.class
			usando EnchufeTresPatas.class como Enchufable.class
			usando Velador.class como Velador.class
			dame Velador.class
			})
		
		def velador = bean
		
		Assert.assertTrue(velador.getLuz() instanceof LamparitasOsram);
		Assert.assertEquals("Cuchuflo", velador.getMarca());
		Assert.assertNull(velador.getAlto());
		Assert.assertEquals(2, velador.getMamparas().size());
	}
	
	@Test
	void "un relation builder tiene todas las relaciones"(){
		def ib = new InjectionBuilder()
		def fixture = { usando LamparitasOsram.class como Alumbrable.class
						usando EnchufeTresPatas.class como Enchufable.class
						usando Velador.class como Velador.class
					   }
		
		ib.with fixture
		Assert.assertEquals(3, ib.relations.size())
		ib.relations.each { 
			Assert.assertNotNull(it.quien)
			Assert.assertNotNull(it.esQue)
		}
	}
	
	@Test
	void "un relation builder sabe relacionar"(){
		def ib = new InjectionBuilder()
		def relBuilder = ib.usando(LamparitasOsram.class)
		Assert.assertNotNull(relBuilder)
		Assert.assertEquals(LamparitasOsram.class, relBuilder.quien)
		relBuilder.como(Alumbrable.class)
		Assert.assertEquals(Alumbrable.class, relBuilder.esQue)
		
		
		Assert.assertEquals(1, ib.relations.size())
		Assert.assertNotNull(ib.relations.get(0).quien)
		Assert.assertNotNull(ib.relations.get(0).esQue)
	}
	
}
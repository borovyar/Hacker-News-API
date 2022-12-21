package cz.cvut.fit.bioop.hackernewsclient.renderer
import java.io.OutputStream

class RendererImpl extends Renderer{
  override def renderToConsole[T](entity: T): Unit = {
    renderToOutputStream(entity, Console.out)
  }

  override def renderToOutputStream[T](entity: T, out: OutputStream): Unit ={
    out.write(entity.toString.getBytes)
    out.flush()
  }
}

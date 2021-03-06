package org.wdm.core.loader

/**
 * A loader that returns an iterator around a web resource
 */
class HttpLoader (val location:String) extends AbstractLoader {

    /**
     * Loads the underlying collection
     *
     * @return An iterator around the given source
     */
    override def load() = io.Source.fromURL(location).getLines
}

/**
 * A factory that is used to test and load a web resource
 */
object HttpLoader extends AbstractLoaderFactory {

    /**
     * Tests if this loader supports loading the resource
     * at the referenced location.
     *
     * @param location The URI that should be tested
     * @return true if this loader supports this location
     */
    override def supports(location:String) =
        location.startsWith("http://")  ||
        location.startsWith("https://") ||
        location.startsWith("ftp://")   ||
        location.startsWith("ftps://")

    /**
     * Creates a loader for the specified location
     *
     * @param location The URI that should be loaded
     * @return A new loader instance
     */
    override def apply(location:String) = new HttpLoader(location)
}

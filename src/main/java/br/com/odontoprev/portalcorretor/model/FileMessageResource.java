package br.com.odontoprev.portalcorretor.model;

import org.springframework.core.io.ByteArrayResource;

public class FileMessageResource extends ByteArrayResource {

    private final String filename;

    public FileMessageResource(final byte[] byteArray, final String filename) {
        super(byteArray);
        this.filename = filename;
    }

    @Override
    public String getFilename() {
        return filename;
    }
}
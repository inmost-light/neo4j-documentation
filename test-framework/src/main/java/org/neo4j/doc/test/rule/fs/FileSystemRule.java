/*
 * Licensed to Neo4j under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Neo4j licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.neo4j.doc.test.rule.fs;

import org.junit.rules.ExternalResource;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.CopyOption;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.neo4j.io.fs.FileHandle;
import org.neo4j.io.fs.FileSystemAbstraction;
import org.neo4j.io.fs.OpenMode;
import org.neo4j.io.fs.StoreChannel;
import org.neo4j.io.fs.watcher.FileWatcher;

public abstract class FileSystemRule<FS extends FileSystemAbstraction> extends ExternalResource
        implements FileSystemAbstraction, Supplier<FileSystemAbstraction>
{
    protected volatile FS fs;

    protected FileSystemRule( FS fs )
    {
        this.fs = fs;
    }

    @Override
    protected void after()
    {
        try
        {
            fs.close();
        }
        catch ( IOException e )
        {
            throw new UncheckedIOException( e );
        }
        super.after();
    }

    @Override
    public FS get()
    {
        return fs;
    }

    @Override
    public void close() throws IOException
    {
        fs.close();
    }

    @Override
    public FileWatcher fileWatcher() throws IOException
    {
        return fs.fileWatcher();
    }

    @Override
    public StoreChannel open( File fileName, OpenMode openMode ) throws IOException
    {
        return fs.open( fileName, openMode );
    }

    @Override
    public OutputStream openAsOutputStream( File fileName, boolean append ) throws IOException
    {
        return fs.openAsOutputStream( fileName, append );
    }

    @Override
    public InputStream openAsInputStream( File fileName ) throws IOException
    {
        return fs.openAsInputStream( fileName );
    }

    @Override
    public Reader openAsReader( File fileName, Charset charset ) throws IOException
    {
        return fs.openAsReader( fileName, charset );
    }

    @Override
    public Writer openAsWriter( File fileName, Charset charset, boolean append ) throws IOException
    {
        return fs.openAsWriter( fileName, charset, append );
    }

    @Override
    public StoreChannel create( File fileName ) throws IOException
    {
        return fs.create( fileName );
    }

    @Override
    public boolean fileExists( File fileName )
    {
        return fs.fileExists( fileName );
    }

    @Override
    public boolean mkdir( File fileName )
    {
        return fs.mkdir( fileName );
    }

    @Override
    public void mkdirs( File fileName ) throws IOException
    {
        fs.mkdirs( fileName );
    }

    @Override
    public long getFileSize( File fileName )
    {
        return fs.getFileSize( fileName );
    }

    @Override
    public boolean deleteFile( File fileName )
    {
        return fs.deleteFile( fileName );
    }

    @Override
    public void deleteRecursively( File directory ) throws IOException
    {
        fs.deleteRecursively( directory );
    }

    @Override
    public void renameFile( File from, File to, CopyOption... copyOptions ) throws IOException
    {
        fs.renameFile( from, to, copyOptions );
    }

    @Override
    public File[] listFiles( File directory )
    {
        return fs.listFiles( directory );
    }

    @Override
    public File[] listFiles( File directory, FilenameFilter filter )
    {
        return fs.listFiles( directory, filter );
    }

    @Override
    public boolean isDirectory( File file )
    {
        return fs.isDirectory( file );
    }

    @Override
    public void moveToDirectory( File file, File toDirectory ) throws IOException
    {
        fs.moveToDirectory( file, toDirectory );
    }

    @Override
    public void copyToDirectory( File file, File toDirectory ) throws IOException
    {
        fs.copyToDirectory( file, toDirectory );
    }

    @Override
    public void copyFile( File from, File to ) throws IOException
    {
        fs.copyFile( from, to );
    }

    @Override
    public void copyFile( File from, File to, CopyOption... copyOptions ) throws IOException
    {
        fs.copyFile( from, to, copyOptions );
    }

    @Override
    public void copyRecursively( File fromDirectory, File toDirectory ) throws IOException
    {
        fs.copyRecursively( fromDirectory, toDirectory );
    }

    @Override
    public void truncate( File path, long size ) throws IOException
    {
        fs.truncate( path, size );
    }

    @Override
    public long lastModifiedTime( File file )
    {
        return fs.lastModifiedTime( file );
    }

    @Override
    public void deleteFileOrThrow( File file ) throws IOException
    {
        fs.deleteFileOrThrow( file );
    }

    @Override
    public Stream<FileHandle> streamFilesRecursive( File directory ) throws IOException
    {
        return fs.streamFilesRecursive( directory );
    }

    @Override
    public int hashCode()
    {
        return fs.hashCode();
    }

    @Override
    public boolean equals( Object obj )
    {
        return fs.equals( obj );
    }

    @Override
    public String toString()
    {
        return fs.toString();
    }
}

/**
 *     This file is part of JObexFTP.
 *
 *    JObexFTP is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU Lesser General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    JObexFTP is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU Lesser General Public License for more details.
 *
 *    You should have received a copy of the GNU Lesser General Public License
 *    along with JObexFTP.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.lhf.obex;

import com.lhf.obex.dao.OBEXFile;

/**
 * Basic structure of OBEX client
 * @author Ricardo Guilherme Schmidt
 */
public interface OBEXInterface {

    /** Default connection header to send */
    static final byte[] OBEX_CONNECT = {(byte) 0x80, (byte) 00, (byte) 0x1a, (byte) 0x10, (byte) 00, (byte) 0x40, 6, (byte) 0x46, (byte) 00, (byte) 0x13, (byte) 0x6B, 1, (byte) 0xCB, (byte) 0x31, (byte) 0x41, 6, (byte) 0x11, (byte) 0xD4, (byte) 0x9A, (byte) 0x77, 0, (byte) 0x50, (byte) 0xDA, (byte) 0x3F, (byte) 0x47, (byte) 0x1f};
    /** Setpath backyards hex "byte" */
    static final int SETPATH2_OPCODE = 0x86;
    /** Setpath forwards hex "byte" */
    static final int SETPATH_OPCODE = 0x85;
    /** Abort hex "byte" */
    static final int ABORT_OPCODE = 0x84;
    /** Get hex "byte" */
    static final int GET_OPCODE = 0x83;
    /** Put hex "byte" */
    static final int PUT_OPCODE = 0x82;
    /** Disconnect hex "byte" */
    static final int DISCONNECT_OPCODE = 0x81;
    /** Connect hex "byte" */
    static final int CONNECT_OPCODE = 0x80;
    /** a put delete operation */
    static final int PUT_DELETE = 0;
    /** a put change operation */
    static final int PUT_CHANGE = 1;
    /** a put send first block operation */
    static final int PUT_SEND = 2;
    /** a put send next blocks operation */
    static final int PUT_SEND_MORE = 3;
    /** a put format operation*/
    static final int PUT_FORMAT = 4;
    /**Format filesystem hex sequence*/
    static final byte[] FORMAT = {(byte) 0x82, (byte) 0x00, (byte) 0x08, (byte) 0x4c, (byte) 0x00, (byte) 0x05, (byte) 0x31, (byte) 0x00};
    /** no flow control */
    static final int FLOW_NONE = 0;
    /** XonXoff flow control */
    static final int FLOW_XONXOFF = 1;
    /** RtsCts flow control */
    static final int FLOW_RTSCTS = 2;

    /**
     * Changes the actual path in the obexconnection
     * @param path to be changed
     * @param backwards if your going back in dirs (not tested yet)
     * @param create if you want to create a new dir if it not exsits
     * @return array of integers with the obex response
     * @throws OBEXException if operation was not successful
     */
    byte[] setPath(String path, boolean backwards, boolean create) throws OBEXException;

    /**
     * function for obex get operations
     * @param what you want to get
     * @return what you got
     * @throws OBEXException if operation was not successful
     */
    byte[] get(byte[] what) throws OBEXException;

    /**
     * function for obex put operation
     * @param type of the put operation you want to realize
     * @param object the object you want to send or change settings
     * @return true if could put, false if not.
     * @throws OBEXException if operation was not successful
     */
    boolean put(int type, OBEXFile object) throws OBEXException;

    /**
     * function to abort obex operations
     * @return the obex response
     * @throws OBEXException if operation was not successful
     */
    byte[] abort() throws OBEXException;

    /**
     * function to format the flash filesystem
     * @return true if the format was successful
     * @throws OBEXException if the operation was not successful
     */
    boolean format() throws OBEXException;

    /**
     * function to open a obex connection
     * @return true if connection was successful
     * @throws OBEXException if operation was not successful
     */
    boolean enterOBEXMode() throws OBEXException;

    /**
     * function to leaveOBEXMode from obex server
     * @return true if disconnection was accepted
     * @throws OBEXException if operation was not successful
     */
    boolean leaveOBEXMode() throws OBEXException;
}

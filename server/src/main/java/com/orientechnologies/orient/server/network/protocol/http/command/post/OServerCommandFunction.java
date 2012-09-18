/*
 * Copyright 2010-2012 Luca Garulli (l.garulli--at--orientechnologies.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.orientechnologies.orient.server.network.protocol.http.command.post;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.server.db.OSharedDocumentDatabase;
import com.orientechnologies.orient.server.network.protocol.http.OHttpRequest;
import com.orientechnologies.orient.server.network.protocol.http.OHttpUtils;
import com.orientechnologies.orient.server.network.protocol.http.command.OServerCommandAuthenticatedDbAbstract;

public class OServerCommandFunction extends OServerCommandAuthenticatedDbAbstract {
  private static final String[] NAMES = { "GET|function/*", "POST|function/*" };

  @Override
  public boolean execute(final OHttpRequest iRequest) throws Exception {
    checkSyntax(iRequest.url, 2, "Syntax error: function/<database>");

    iRequest.data.commandInfo = "Execute a function";

    ODatabaseDocumentTx db = null;
    Object result = null;

    try {
      db = getProfiledDatabaseInstance(iRequest);

    } finally {
      if (db != null)
        OSharedDocumentDatabase.release(db);
    }

    sendTextContent(iRequest, OHttpUtils.STATUS_CREATED_CODE, OHttpUtils.STATUS_CREATED_DESCRIPTION, null,
        OHttpUtils.CONTENT_TEXT_PLAIN, result, true, null);
    return false;
  }

  @Override
  public String[] getNames() {
    return NAMES;
  }
}
package net.sf.colorer.editor;

/**
 * 
 *
 */
public interface IFoldingReciever {

	/**
	 * Called, when new folding item should be created
	 * 
	 * @param s_line Line for folding start
	 * @param s_offset Line offset for folding start
	 * @param e_line Line for folding start
	 * @param e_offset Line offset for folding start
	 * @param schema Folding content type information in terms of Colorer's HRC Schema.
	 */
	public void notifyFoldingItem(int s_line, int s_offset, int e_line, int e_offset, String schema);

	/**
	 * Called for line lineno, below which all folding structure
	 * should be invalidated.
	 * @param lineno
	 */
	public void notifyInvalidate(int lineno);

}

/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is the Colorer Library
 *
 * The Initial Developer of the Original Code is
 * Igor Russkih <irusskih at gmail dot com>.
 * Portions created by the Initial Developer are Copyright (C) 1999-2007
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 *
 * ***** END LICENSE BLOCK ***** */

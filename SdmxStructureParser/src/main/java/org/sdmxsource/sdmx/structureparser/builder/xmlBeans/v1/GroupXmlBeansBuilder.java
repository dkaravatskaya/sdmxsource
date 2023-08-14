/*******************************************************************************
 * Copyright (c) 2013 Metadata Technology Ltd.
 *
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the GNU Lesser General Public License v 3.0 
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * This file is part of the SDMX Component Library.
 *
 * The SDMX Component Library is free software: you can redistribute it and/or 
 * modify it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * The SDMX Component Library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser 
 * General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License 
 * along with The SDMX Component Library If not, see 
 * http://www.gnu.org/licenses/lgpl.
 *
 * Contributors:
 * Metadata Technology - initial API and implementation
 ******************************************************************************/
package org.sdmxsource.sdmx.structureparser.builder.xmlBeans.v1;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.apache.xmlbeans.XmlNMTOKEN;
import org.sdmx.resources.sdmxml.schemas.v10.xmlbeans.structure.GroupType;
import org.sdmxsource.sdmx.api.builder.Builder;
import org.sdmxsource.sdmx.api.exception.SdmxException;
import org.sdmxsource.sdmx.api.model.beans.datastructure.GroupBean;
import org.sdmxsource.util.ObjectUtil;


/**
 * The type Group xml beans builder.
 */
public class GroupXmlBeansBuilder extends AbstractBuilder implements Builder<GroupType, GroupBean> {

    static {
        log = LoggerFactory.getLogger(GroupXmlBeansBuilder.class);
    }

    @Override
    public GroupType build(GroupBean buildFrom) throws SdmxException {
        GroupType builtObj = GroupType.Factory.newInstance();
        if (validString(buildFrom.getId())) {
            builtObj.setId(buildFrom.getId());
        }
        if (hasAnnotations(buildFrom)) {
            builtObj.setAnnotations(getAnnotationsType(buildFrom));
        }
        if (ObjectUtil.validCollection(buildFrom.getDimensionRefs())) {
            for (String dimensionRef : buildFrom.getDimensionRefs()) {
                XmlNMTOKEN idType = builtObj.addNewDimensionRef();
                idType.setStringValue(dimensionRef);
            }
        }

        return builtObj;
    }
}

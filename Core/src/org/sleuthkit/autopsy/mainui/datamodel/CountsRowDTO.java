/*
 * Autopsy Forensic Browser
 *
 * Copyright 2021 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
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
package org.sleuthkit.autopsy.mainui.datamodel;

import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.List;
import org.openide.util.NbBundle.Messages;

/**
 *
 * A row result providing a category and a count for that category.
 */
@Messages({
    "CountsRowResultDTO_columns_displayName_name=displayName",
    "CountsRowResultDTO_columns_displayName_displayName=Name",
    "CountsRowResultDTO_columns_displayName_description=Name",
    "CountsRowResultDTO_columns_count_name=displayName",
    "CountsRowResultDTO_columns_count_displayName=Name",
    "CountsRowResultDTO_columns_count_description=Name"
})
public class CountsRowDTO<T> implements RowDTO {

    private static final String DEFAULT_TYPE_ID = "COUNTS";
    
    public static ColumnKey DISPLAY_NAME_COL = new ColumnKey(
            Bundle.CountsRowResultDTO_columns_displayName_name(),
            Bundle.CountsRowResultDTO_columns_displayName_displayName(),
            Bundle.CountsRowResultDTO_columns_displayName_description());

    public static ColumnKey COUNT_COL = new ColumnKey(
            Bundle.CountsRowResultDTO_columns_count_name(),
            Bundle.CountsRowResultDTO_columns_count_displayName(),
            Bundle.CountsRowResultDTO_columns_count_description());

    private final long id;
    private final String displayName;
    private final long count;
    private final List<Object> cellValues;
    private final String typeId;
    private final T typeData;

    public CountsRowDTO(T typeData, long id, String displayName, long count) {
        this(DEFAULT_TYPE_ID, typeData, id, displayName, count);
    }
    
    public CountsRowDTO(String typeId, T typeData, long id, String displayName, long count) {
        this.typeId = typeId;
        this.id = id;
        this.displayName = displayName;
        this.count = count;
        this.cellValues = ImmutableList.of(Arrays.asList(displayName, count));
        this.typeData = typeData;
    }

    @Override
    public long getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public long getCount() {
        return count;
    }

    @Override
    public List<Object> getCellValues() {
        return cellValues;
    }

    @Override
    public String getTypeId() {
        return typeId;
    }

    public T getTypeData() {
        return typeData;
    }
    
    
}

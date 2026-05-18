/**
 * The contents of this file are subject to the Mozilla Public License Version 1.1 (the "License");
 * you may not use this file except in compliance with the License. You may obtain a copy of the
 * License at http://www.mozilla.org/MPL/
 *
 * <p>Software distributed under the License is distributed on an "AS IS" basis, WITHOUT WARRANTY OF
 * ANY KIND, either express or implied. See the License for the specific language governing rights
 * and limitations under the License.
 *
 * <p>The Original Code is OpenELIS code.
 *
 * <p>Copyright (C) CIRG, University of Washington, Seattle WA. All Rights Reserved.
 */
package org.openelisglobal.common.provider.validation;

import org.openelisglobal.common.provider.validation.AccessionNumberValidatorFactory.AccessionFormat;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.DateUtil;

/**
 * Accession format: {prefix}-{YY}-{%07d seq}
 *
 * <p>Example: L-NCR01-26-0000001
 *
 * <p>Admin sets "Accession number prefix" = "L-{SiteCode}" (e.g. "L-NCR01") in Site Information.
 * The validator appends -{YY}-{7-digit-seq} automatically. Sequence resets to 1 on year rollover.
 * Capacity: 9,999,999 samples/year.
 *
 * <p>Logged in plan/UPSTREAM-PATCHES.md as P-004. Approved Jun 2026-05-18.
 */
public class PrefixDashYearNumAccessionValidator extends BaseSiteYearAccessionValidator
        implements IAccessionNumberGenerator {

    @Override
    public int getMaxAccessionLength() {
        // prefix + "-" + YY + "-" + 7 seq digits = prefix.length + 11
        return getPrefix().length() + 11;
    }

    @Override
    public int getMinAccessionLength() {
        return getMaxAccessionLength();
    }

    @Override
    protected int getIncrementStartIndex() {
        // skip: prefix + "-" + YY + "-"
        return getPrefix().length() + 4;
    }

    @Override
    protected int getSiteEndIndex() {
        return getPrefix().length();
    }

    @Override
    protected int getYearEndIndex() {
        // prefix + "-" + YY
        return getPrefix().length() + 3;
    }

    @Override
    protected int getYearStartIndex() {
        // skip prefix + "-"
        return getPrefix().length() + 1;
    }

    @Override
    public int getInvarientLength() {
        return getPrefix().length();
    }

    @Override
    public int getChangeableLength() {
        // "-" + YY + "-" + 7 seq digits
        return 11;
    }

    @Override
    public String getPrefix() {
        return ConfigurationProperties.getInstance()
                .getPropertyValue(ConfigurationProperties.Property.ACCESSION_NUMBER_PREFIX);
    }

    @Override
    public String incrementAccessionNumber() throws IllegalArgumentException {
        String year = DateUtil.getTwoDigitYear();
        long nextNum = accessionService.getNextNumberIncrement(getPrefix() + year,
                AccessionFormat.PREFIX_DASH_YEARNUM);
        return String.format("%s-%s-%07d", getPrefix(), year, nextNum);
    }

    @Override
    public String incrementAccessionNumberNoReserve() throws IllegalArgumentException {
        String year = DateUtil.getTwoDigitYear();
        long nextNum = accessionService.getNextNumberNoIncrement(getPrefix() + year,
                AccessionFormat.PREFIX_DASH_YEARNUM);
        return String.format("%s-%s-%07d", getPrefix(), year, nextNum);
    }
}

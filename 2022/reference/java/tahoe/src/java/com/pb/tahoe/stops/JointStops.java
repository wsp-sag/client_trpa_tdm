/*
 * Copyright  2005 PB Consult Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package com.pb.tahoe.stops;

import com.pb.tahoe.structures.Household;
import com.pb.tahoe.structures.TourType;
import com.pb.tahoe.util.HouseholdArrayManager;

import java.util.ResourceBundle;

/**
 * JointStops is a class that ...
 *
 * @author Christi Willison
 * @version 1.0,  Oct 16, 2006
 */
public class JointStops {

    private StopsHousehold stopsHH;
    private HouseholdArrayManager hhMgr;




    public JointStops (ResourceBundle propertyMap, HouseholdArrayManager hhMgr) {


        this.hhMgr = hhMgr;

        stopsHH = new StopsHousehold ( propertyMap, TourType.JOINT_CATEGORY, TourType.getTourTypesForCategory(TourType.JOINT_CATEGORY ));

    }



    public void doSfcSlcWork() {

        // get the list of households to be processed
        Household[] hhList = hhMgr.getHouseholds();

        if (hhList == null)
            return;

        for (int i=1; i < hhList.length; i++) {
            stopsHH.jointTourSfcSlc ( hhList[i] );
        }

        stopsHH.closeSlfSlcOutputStreams();

        hhMgr.sendResults ( hhList );
    }



    public void doSmcWork() {

        // get the list of households to be processed
        Household[] hhList = hhMgr.getHouseholds();

        if (hhList == null)
            return;

        for (int i=1; i < hhList.length; i++) {
            stopsHH.jointTourSmc ( hhList[i] );
        }

        stopsHH.closeSmcOutputStreams();

        hhMgr.sendResults ( hhList );
    }



    public void printTimes ( short tourTypeCategory ) {

        stopsHH.printTimes( tourTypeCategory );

    }
}

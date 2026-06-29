import React from "react";
import { Card, CardContent } from "@/components/ui/card";

const shiftData = {
  cashier: {
    fullName: "Pablo Pandey",
  },
  shiftStart: "June 29, 2026 6:54 PM",
  shiftEnd: "",
};

const ShiftInformation = () => {
  return (
    <div>
      <Card>
        <CardContent>
          <h2 className="text-xl font-semibold mb-4">Shift Information</h2>

          <div className="space-y-2">
            {/* Cashier */}
            <div className="flex justify-between">
              <span className="text-muted-foreground">Cashier:</span>
              <span className="font-medium">
                {shiftData.cashier.fullName}
              </span>
            </div>

            {/* Shift Start */}
            <div className="flex justify-between">
              <span className="text-muted-foreground">Shift Start:</span>
              <span className="font-medium">{shiftData.shiftStart}</span>
            </div>

            {/* Shift End */}
            <div className="flex justify-between">
              <span className="text-muted-foreground">Shift End:</span>
              <span className="font-medium">
                {shiftData.shiftEnd ? shiftData.shiftEnd : "ongoing"}
              </span>
            </div>

            {/* Duration */}
            <div className="flex justify-between">
              <span className="text-muted-foreground">Duration:</span>
              <span className="font-medium">8 hours</span>
            </div>
          </div>
        </CardContent>
      </Card>
    </div>
  );
};

export default ShiftInformation;
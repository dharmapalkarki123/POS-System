import { Card, CardContent } from '@/components/ui/card'
import React from 'react'


const shiftData = {
  cashier: {
    fullName: "Pablo Pandey",
  },
  shiftStart: "June 29, 2026 6:54 PM",
  shiftEnd: "",
  totalOrders: 50,
  totalSales: 500.00,
  totalRefunds: 4689,
  netSale: 322,
};

const SalesSummaryCard = () => {
  return (
    <div>

      <Card>
       <CardContent>
         <h2 className="text-xl font-semibold mb-4">Sales Summary</h2>
       
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
                     <span className="text-muted-foreground">Total Orders:</span>
                     <span className="font-medium">{shiftData.totalOrders}</span>
                   </div>
       
                   {/* Shift End */}
                   <div className="flex justify-between">
                     <span className="text-muted-foreground">Total Sales:</span>
                     <span className="font-medium">
                       {shiftData.totalSales}
                     </span>
                   </div>
       
                   {/* Duration */}
                   <div className="flex justify-between">
                     <span className="text-muted-foreground">Total Refunds:</span>
                     <span className="font-medium">{shiftData.totalRefunds}</span>
                   </div>



                    <div className="flex justify-between">
                     <span className="text-muted-foreground">Net Sales:</span>
                     <span className="font-medium">{shiftData.netSale}</span>
                   </div>




                 </div>
       </CardContent>
      </Card>
      
    </div>
  )
}

export default SalesSummaryCard

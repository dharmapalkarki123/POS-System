import React from 'react'
import ShiftHeaderReport from './ShiftHeaderReport'
import ShiftInformation from './ShiftInformation'
import SalesSummaryCard from './SalesSummaryCard'
import PaymentSummaryCard from './PaymentSummaryCard'
import TopsellingItemsCard from './TopSellingItemsCard'
import RecentOrderTable from './RecentOrderTable'
import RefundsTable from './RefundsTable'

const ShiftSummaryPage = () => {
  return (
    <div className="h-screen flex flex-col bg-gray-100">

      <ShiftHeaderReport />
      
      <div className="flex-1 overflow-auto p-4">
        
        {/* Row 1 */}
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-6">
          <ShiftInformation />
          <SalesSummaryCard />
        </div>

        {/* Row 2 */}
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-6">
          <PaymentSummaryCard />
          <TopsellingItemsCard />
        </div>

        {/* Row 3 */}
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-6">
          <RecentOrderTable />
          <RefundsTable />
        </div>

      </div>
    </div>
  )
}

export default ShiftSummaryPage
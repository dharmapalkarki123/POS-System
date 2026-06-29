import React from 'react'
import { Button } from '@/components/ui/button'
import { ArrowRight } from 'lucide-react'

const ShiftHeaderReport = () => {
  return (
    <div className="p-4 bg-card border-b">
      
      <div className="flex justify-between items-center">
        
        {/* Title */}
        <h1 className="text-2xl font-bold">
          Shift Summary Report
        </h1>

        {/* Action Button */}
        <Button variant="destructive" className="flex items-center gap-2">
          <ArrowRight size={18} />
          End Shift & Logout
        </Button>

      </div>

    </div>
  )
}

export default ShiftHeaderReport
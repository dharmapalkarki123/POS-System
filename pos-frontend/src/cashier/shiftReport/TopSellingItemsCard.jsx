import React from 'react'
import { Card, CardContent } from '@/components/ui/card'

const shiftData = {
  topSellingProducts: [
    {
      id: "2",
      name: "Men Geometric Print Polo Neck Pure Cotton Black T-Shirt",
      sellingPrice: 899,
      quantity: 5,
    },
    {
      name: "Women Geometric Print Polo Neck Pure Cotton Black T-Shirt",
      sellingPrice: 499,
      quantity: 9,
    },
  ],
};


const TopsellingItemsCard = () => {
  return (
   <Card>
  <CardContent>
    <h2 className="text-xl font-semibold mb-4">Top Selling Items</h2>
    <div className="space-y-3">
      {shiftData.topSellingProducts.map((product, index) =>
        <div key={product.id} className="flex items-center">
          
          <div className="w-6 h-6 rounded-full bg-primary/10 flex items-center justify-center mr-3 text-sm font-medium">
            {index + 1}
          </div>
          <div className="flex-1">
  <div className="flex justify-between">
    <span>{product.name}</span>
    <span>${product.sellingPrice}</span>
  </div>
  <div className="flex justify-between text-sm text-muted-foreground">
    <span>{product.quantity} units sold</span>
  </div>
</div>

        </div>
      )}
    </div>
  </CardContent>
</Card>
  )
}

export default TopsellingItemsCard

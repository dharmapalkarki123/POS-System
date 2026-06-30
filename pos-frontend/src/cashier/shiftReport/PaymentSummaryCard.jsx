import React from 'react'
import { Card, CardContent } from '@/components/ui/card'
import { CreditCard } from 'lucide-react' 

const shiftData = {

  paymentSummaries:[
    {


      type:"CASH",
      totalAmount: 1000,
      transactionCount:10

    },
    {
      type:"CARD",
      totalAmount: 2000,
      transactionCount:20
    },
     {
      type:"UPI",
      totalAmount: 2000,
      transactionCount:8
    }
  ],
    totalSales: 90.00,


}


const PaymentSummaryCard = () => {
  return (
     <Card>
       <CardContent className={"p-6"}>
        <h2 className="text-xl font-semibold mb-4">Payment Summary</h2>
        <div className="space-y-4">


          {
            shiftData.paymentSummaries.map((payment) => 
              
              <div className='flex items-center' key={payment.type}>

                <div className='w-10 h-10 rounded-full bg-primary/10 flex items-center justify-center'  >

                <CreditCard/>


                  </div>


                  <div className='flex-1'>
                    <div className='flex justify-between'>
                      <span className='font-medium'>{payment.type}</span>
                      <span className='font-medium'>${payment.totalAmount}</span>
                      </div>

                      <div className='flex justify-between text-sm text-muted-foreground'>
                         <span>Transactions: {payment.transactionCount}</span>
                         <span>{(payment.totalAmount/shiftData.totalSales*100).toFixed(1)} %</span>
                          

                        </div>


                      
                    </div>

                </div>
              
)
          }






        </div>


       </CardContent>



    </Card>
  )
}

export default PaymentSummaryCard

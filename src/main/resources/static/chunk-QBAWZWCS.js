import{J as a}from"./chunk-5ZY26D6C.js";var n=class d{printSaleBill(t){let e=this.buildSaleBillHTML(t);this.openPrintWindow(e)}printPurchaseBill(t){let e=this.buildPurchaseBillHTML(t);this.openPrintWindow(e)}printPaymentReceipt(t){let e=this.buildPaymentReceiptHTML(t);this.openPrintWindow(e)}openPrintWindow(t){let e=window.open("","_blank","width=800,height=600");e.document.write(t),e.document.close(),e.focus(),setTimeout(()=>{e.print(),e.close()},500)}buildSaleBillHTML(t){let e=t.items.map((o,i)=>`
      <tr>
        <td style="padding:6px 8px;border:1px solid #ddd;">
          ${i+1}
        </td>
        <td style="padding:6px 8px;border:1px solid #ddd;">
          ${o.productName}<br>
          <small style="color:#666;">${o.productCode}</small>
        </td>
        <td style="padding:6px 8px;border:1px solid #ddd;
                   text-align:center;">
          ${o.quantity} ${o.unit}
        </td>
        <td style="padding:6px 8px;border:1px solid #ddd;
                   text-align:right;">
          \u20B9${o.unitPrice}
        </td>
        <td style="padding:6px 8px;border:1px solid #ddd;
                   text-align:right;font-weight:bold;">
          \u20B9${o.totalPrice}
        </td>
      </tr>
    `).join("");return`
      <!DOCTYPE html>
      <html>
      <head>
        <title>Sale Bill \u2014 ${t.invoiceNumber}</title>
        <style>
          * { margin:0; padding:0; box-sizing:border-box; }
          body { font-family: Arial, sans-serif; font-size: 13px;
                 color: #333; padding: 20px; }
          .header { text-align: center; border-bottom: 2px solid #198754;
                    padding-bottom: 12px; margin-bottom: 16px; }
          .header h2 { color: #198754; font-size: 22px; }
          .header p { color: #666; font-size: 12px; margin-top: 2px; }
          .bill-title { background: #198754; color: white;
                        text-align: center; padding: 6px;
                        font-size: 14px; font-weight: bold;
                        margin-bottom: 14px; border-radius: 4px; }
          .info-grid { display: grid; grid-template-columns: 1fr 1fr;
                       gap: 10px; margin-bottom: 14px; }
          .info-box { background: #f8f9fa; padding: 10px;
                      border-radius: 4px; }
          .info-box label { color: #666; font-size: 11px;
                            display: block; }
          .info-box span { font-weight: bold; font-size: 13px; }
          table { width: 100%; border-collapse: collapse;
                  margin-bottom: 12px; }
          thead { background: #198754; color: white; }
          thead th { padding: 8px; text-align: left;
                     font-size: 12px; }
          .totals { margin-left: auto; width: 260px; }
          .totals table { border: none; }
          .totals td { padding: 4px 8px; border: none;
                       font-size: 13px; }
          .totals .grand-total { background: #198754;
                                  color: white; font-weight: bold;
                                  font-size: 15px; }
          .footer { text-align: center; margin-top: 20px;
                    color: #666; font-size: 11px;
                    border-top: 1px solid #ddd; padding-top: 10px; }
          .badge { padding: 3px 8px; border-radius: 4px;
                   font-size: 11px; font-weight: bold; }
          .paid { background: #d1fae5; color: #065f46; }
          .partial { background: #fef3c7; color: #92400e; }
          .pending { background: #fee2e2; color: #991b1b; }
          @media print {
            body { padding: 10px; }
            button { display: none !important; }
          }
        </style>
      </head>
      <body>

        <div class="header">
          <h2>\u{1F33F} AGRO ERP</h2>
          <p>Smart Agriculture Store Management</p>
          <p>Contact: 9800000000 | agroerp@store.com</p>
        </div>

        <div class="bill-title">SALE INVOICE</div>

        <div class="info-grid">
          <div class="info-box">
            <label>Invoice Number</label>
            <span>${t.invoiceNumber}</span>
          </div>
          <div class="info-box">
            <label>Date</label>
            <span>${t.saleDate}</span>
          </div>
          <div class="info-box">
            <label>Customer Name</label>
            <span>${t.customerName}</span>
          </div>
          <div class="info-box">
            <label>Phone</label>
            <span>${t.customerPhone}</span>
          </div>
          <div class="info-box">
            <label>Payment Mode</label>
            <span>${t.paymentMode}</span>
          </div>
          <div class="info-box">
            <label>Payment Status</label>
            <span class="badge ${t.paymentStatus==="PAID"?"paid":t.paymentStatus==="PARTIAL"?"partial":"pending"}">
              ${t.paymentStatus}
            </span>
          </div>
        </div>

        <table>
          <thead>
            <tr>
              <th style="width:40px">#</th>
              <th>Product</th>
              <th style="width:100px;text-align:center">Quantity</th>
              <th style="width:100px;text-align:right">Unit Price</th>
              <th style="width:100px;text-align:right">Total</th>
            </tr>
          </thead>
          <tbody>${e}</tbody>
        </table>

        <div class="totals">
          <table>
            <tr>
              <td>Sub Total:</td>
              <td style="text-align:right">\u20B9${t.totalAmount}</td>
            </tr>
            <tr>
              <td style="color:red">Discount:</td>
              <td style="text-align:right;color:red">
                -\u20B9${t.discountAmount}
              </td>
            </tr>
            <tr class="grand-total">
              <td style="padding:6px 8px">Net Total:</td>
              <td style="text-align:right;padding:6px 8px">
                \u20B9${t.netAmount}
              </td>
            </tr>
            <tr>
              <td style="color:green">Paid:</td>
              <td style="text-align:right;color:green">
                \u20B9${t.paidAmount}
              </td>
            </tr>
            <tr>
              <td style="color:red;font-weight:bold">Due:</td>
              <td style="text-align:right;color:red;font-weight:bold">
                \u20B9${t.dueAmount}
              </td>
            </tr>
          </table>
        </div>

        ${t.notes?`
          <div style="margin-top:12px;padding:8px;
                      background:#f8f9fa;border-radius:4px;">
            <strong>Notes:</strong> ${t.notes}
          </div>
        `:""}

        <div class="footer">
          <p>Thank you for your business!</p>
          <p>This is a computer generated bill.</p>
        </div>

      </body>
      </html>
    `}buildPurchaseBillHTML(t){let e=t.items.map((o,i)=>`
      <tr>
        <td style="padding:6px 8px;border:1px solid #ddd;">
          ${i+1}
        </td>
        <td style="padding:6px 8px;border:1px solid #ddd;">
          ${o.productName}<br>
          <small style="color:#666;">${o.productCode}</small>
        </td>
        <td style="padding:6px 8px;border:1px solid #ddd;
                   text-align:center;">
          ${o.quantity} ${o.unit}
        </td>
        <td style="padding:6px 8px;border:1px solid #ddd;
                   text-align:right;">
          \u20B9${o.unitPrice}
        </td>
        <td style="padding:6px 8px;border:1px solid #ddd;
                   text-align:right;font-weight:bold;">
          \u20B9${o.totalPrice}
        </td>
      </tr>
    `).join("");return`
      <!DOCTYPE html>
      <html>
      <head>
        <title>Purchase Bill \u2014 ${t.invoiceNumber}</title>
        <style>
          * { margin:0; padding:0; box-sizing:border-box; }
          body { font-family: Arial, sans-serif; font-size: 13px;
                 color: #333; padding: 20px; }
          .header { text-align: center; border-bottom: 2px solid #0d6efd;
                    padding-bottom: 12px; margin-bottom: 16px; }
          .header h2 { color: #0d6efd; font-size: 22px; }
          .bill-title { background: #0d6efd; color: white;
                        text-align: center; padding: 6px;
                        font-size: 14px; font-weight: bold;
                        margin-bottom: 14px; border-radius: 4px; }
          .info-grid { display: grid; grid-template-columns: 1fr 1fr;
                       gap: 10px; margin-bottom: 14px; }
          .info-box { background: #f8f9fa; padding: 10px;
                      border-radius: 4px; }
          .info-box label { color: #666; font-size: 11px;
                            display: block; }
          .info-box span { font-weight: bold; font-size: 13px; }
          table { width: 100%; border-collapse: collapse;
                  margin-bottom: 12px; }
          thead { background: #0d6efd; color: white; }
          thead th { padding: 8px; text-align: left;
                     font-size: 12px; }
          .totals { margin-left: auto; width: 260px; }
          .totals td { padding: 4px 8px; font-size: 13px; }
          .grand-total { background: #0d6efd; color: white;
                         font-weight: bold; font-size: 15px; }
          .footer { text-align: center; margin-top: 20px;
                    color: #666; font-size: 11px;
                    border-top: 1px solid #ddd; padding-top: 10px; }
          @media print { body { padding: 10px; } }
        </style>
      </head>
      <body>

        <div class="header">
          <h2>\u{1F33F} AGRO ERP</h2>
          <p>Smart Agriculture Store Management</p>
        </div>

        <div class="bill-title">PURCHASE INVOICE</div>

        <div class="info-grid">
          <div class="info-box">
            <label>Invoice Number</label>
            <span>${t.invoiceNumber}</span>
          </div>
          <div class="info-box">
            <label>Date</label>
            <span>${t.purchaseDate}</span>
          </div>
          <div class="info-box">
            <label>Supplier</label>
            <span>${t.supplierName}</span>
          </div>
          <div class="info-box">
            <label>Payment Status</label>
            <span>${t.paymentStatus}</span>
          </div>
        </div>

        <table>
          <thead>
            <tr>
              <th style="width:40px">#</th>
              <th>Product</th>
              <th style="width:100px;text-align:center">Quantity</th>
              <th style="width:100px;text-align:right">Unit Price</th>
              <th style="width:100px;text-align:right">Total</th>
            </tr>
          </thead>
          <tbody>${e}</tbody>
        </table>

        <div class="totals">
          <table>
            <tr class="grand-total">
              <td style="padding:6px 8px">Total Amount:</td>
              <td style="text-align:right;padding:6px 8px">
                \u20B9${t.totalAmount}
              </td>
            </tr>
            <tr>
              <td style="color:green">Paid:</td>
              <td style="text-align:right;color:green">
                \u20B9${t.paidAmount}
              </td>
            </tr>
            <tr>
              <td style="color:red;font-weight:bold">Due:</td>
              <td style="text-align:right;color:red;font-weight:bold">
                \u20B9${t.dueAmount}
              </td>
            </tr>
          </table>
        </div>

        <div class="footer">
          <p>This is a computer generated purchase bill.</p>
        </div>

      </body>
      </html>
    `}buildPaymentReceiptHTML(t){return`
      <!DOCTYPE html>
      <html>
      <head>
        <title>Payment Receipt \u2014 ${t.paymentNumber}</title>
        <style>
          * { margin:0; padding:0; box-sizing:border-box; }
          body { font-family: Arial, sans-serif; font-size: 13px;
                 color: #333; padding: 20px; max-width: 400px;
                 margin: 0 auto; }
          .header { text-align: center;
                    border-bottom: 2px solid #198754;
                    padding-bottom: 12px; margin-bottom: 16px; }
          .header h2 { color: #198754; font-size: 20px; }
          .receipt-title { background: #198754; color: white;
                           text-align: center; padding: 6px;
                           font-size: 14px; font-weight: bold;
                           margin-bottom: 16px; border-radius: 4px; }
          .row { display: flex; justify-content: space-between;
                 padding: 8px 0;
                 border-bottom: 1px solid #f0f0f0; }
          .row label { color: #666; }
          .row span { font-weight: bold; }
          .amount-box { background: #d1fae5; border: 2px solid #198754;
                        border-radius: 8px; text-align: center;
                        padding: 16px; margin: 16px 0; }
          .amount-box .label { color: #065f46; font-size: 12px; }
          .amount-box .amount { color: #065f46; font-size: 28px;
                                font-weight: bold; }
          .footer { text-align: center; margin-top: 16px;
                    color: #666; font-size: 11px; }
          @media print { body { padding: 5px; } }
        </style>
      </head>
      <body>

        <div class="header">
          <h2>\u{1F33F} AGRO ERP</h2>
          <p>Smart Agriculture Store Management</p>
        </div>

        <div class="receipt-title">PAYMENT RECEIPT</div>

        <div class="amount-box">
          <div class="label">Amount Received</div>
          <div class="amount">\u20B9${t.amount}</div>
        </div>

        <div class="row">
          <label>Receipt No</label>
          <span>${t.paymentNumber}</span>
        </div>
        <div class="row">
          <label>Date</label>
          <span>${t.paymentDate}</span>
        </div>
        <div class="row">
          <label>Customer</label>
          <span>${t.customerName}</span>
        </div>
        <div class="row">
          <label>Phone</label>
          <span>${t.customerPhone}</span>
        </div>
        <div class="row">
          <label>Against Invoice</label>
          <span>${t.saleInvoiceNumber||"General Payment"}</span>
        </div>
        <div class="row">
          <label>Payment Mode</label>
          <span>${t.paymentMode}</span>
        </div>
        ${t.referenceNumber?`
          <div class="row">
            <label>Reference</label>
            <span>${t.referenceNumber}</span>
          </div>
        `:""}
        ${t.notes?`
          <div class="row">
            <label>Notes</label>
            <span>${t.notes}</span>
          </div>
        `:""}

        <div class="footer">
          <p>Thank you for your payment!</p>
          <p>This is a computer generated receipt.</p>
        </div>

      </body>
      </html>
    `}static \u0275fac=function(e){return new(e||d)};static \u0275prov=a({token:d,factory:d.\u0275fac,providedIn:"root"})};export{n as a};

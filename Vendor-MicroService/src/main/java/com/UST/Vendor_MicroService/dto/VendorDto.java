package com.UST.Vendor_MicroService.dto;

public class VendorDto {
    private String vendorId;
    private Long quantity;
    public VendorDto(String vendorId, Long quantity) {
        this.vendorId = vendorId;
        this.quantity = quantity;
    }
    public String getVendorId() {return vendorId;}
    public Long getQuantity() {return quantity;}
    public void setVendorId(String vendorId) {this.vendorId = vendorId;}
    public void setQuantity(Long quantity) {this.quantity = quantity;}
    public VendorDto() {}
}

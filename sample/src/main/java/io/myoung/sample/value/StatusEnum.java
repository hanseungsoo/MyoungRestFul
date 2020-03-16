package io.myoung.sample.value;

public enum StatusEnum {
	
	ERROR("ERROR"), SUCCESS("SUCCESS"), VALID("VALID");
	
	final private String name;
    
    private StatusEnum(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}

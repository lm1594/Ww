/* --------------------------------------------------------------------------
 * NexChange(TM) META ver 4.0
 * Copyright(c) 2019 E4NET.
 *
 * All rights are reserved.  This program follows commercial software license.
 * Anyone wants to use this S/W is required to get appropriate permission.
 * The program is provided "as is" without any warranty express or
 * implied, including the warranty of non-infringement and the implied
 * warranties of merchantability and fitness for a particular purpose.
 * E4NET will not be liable for any damages caused by using the Program.
 * -------------------------------------------------------------------------- */
package lkm.ww.comn.vo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Base Value Object
 * @author 이포넷(이득경)
 * @since 2019. 01. 30.
 */
public class BaseVO extends PageVO {
	
	private	final 	static	Class<String>[] NONE_PARAM = new Class[0];
    private	final	static	Class<String>[] STRING_PARAM = new Class[] {String.class};
    
    /**
     * {@code vo}클래스 멤버 변수 중에서 {@code String} 형식의 값이 {@code null}인 경우 {@code nullVal} 값으로 모두 채운다.<br/>
     * 1. final 변수는 제외됨.<br>
     * 2. 클래스에는 getter, setter 함수가 포함되어 있어야 함.<br/>
     * @param vo 클래스 변수
     * @param nullVal 멤버 변수가 {@code null}인 경우 채울 값
     */
    public void nullToString(String nullVal) {
        Class<?> clz = this.getClass();
       
        do {
            nullToString(clz, nullVal);
            clz = clz.getSuperclass();
        } while ((clz!=null)&&(clz!=Object.class));
	}
    
    private void nullToString(Class<?> clz, String nullVal) {
		Field[] fields = clz.getDeclaredFields();	// 선언된 모든 속성 필드를 찾아온다. // clz.getFields(): 접근가능(public)한 속성 필드만 찾아 온다.
        for(int ix=0; ix < fields.length; ix++) {
        	Field field = fields[ix];
        	if ( (field.getModifiers() & Modifier.FINAL) != 0 ) continue;
        	if ( field.getType() == String.class || field.getType() == String[].class ) {	// String 타입인 경우에 대해서만 null 확인
    			String fldName = field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
    			try {
    				//---------------------------
    				// String get???()
    				//---------------------------
    				Method method = clz.getDeclaredMethod("get"+fldName, NONE_PARAM);		// in case of String
    				if ( method != null ) {
    					if ( method.getReturnType() == String.class ) {
	    					String val = (String)method.invoke(this);
	    					if ( val == null ) {		// 현재 프로퍼티의 값이 null 이면 ?
	    	    				//---------------------------
	    	    				// set???(String)
	    	    				//---------------------------
			    				String setname = "set"+fldName;
			    				method = clz.getDeclaredMethod(setname, STRING_PARAM);
			    				if ( method != null ) {
			    					method.invoke(this, new String[]{nullVal});
			    				}
	    					}
    					} else if ( method.getReturnType() == String[].class ) {
	    					String[] a = (String[])method.invoke(this);
	    					if ( a != null ) {
	    						for(int i=0; i < a.length; i++) {
	    							if ( a[i] == null ) a[i] = nullVal;
	    						}
	    						// 주소로 값을 변경했으므로, 값을 쓰기위한 invoke() 호출 필요 없음
	    					}
    					}
	    			}
    			} catch ( Throwable t ) {
    				//
    			}
    		}
        }
	}

    public void trim() {
        Class<?> clz = this.getClass();
        do {
            trim(clz);
            clz = clz.getSuperclass();
        } while ((clz!=null)&&(clz!=Object.class));
	}
    
    private void trim(Class<?> clz) {
		Field[] fields = clz.getDeclaredFields();	// 선언된 모든 속성 필드를 찾아온다. // clz.getFields(): 접근가능(public)한 속성 필드만 찾아 온다.
        for(int ix=0; ix < fields.length; ix++) {
        	Field field = fields[ix];
        	if ( (field.getModifiers() & Modifier.FINAL) != 0 ) continue;
    		if ( field.getType() == String.class || field.getType() == String[].class ) {	// String 타입인 경우에 대해서만 null 확인
    			String fldName = field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
    			try {
    				//---------------------------
    				// String get???()
    				//---------------------------
    				Method method = clz.getDeclaredMethod("get"+fldName, NONE_PARAM);		// in case of String
    				if ( method != null ) {
    					if ( method.getReturnType() == String.class ) {
	    					String val = (String)method.invoke(this);
	    					if ( val != null ) {		// 현재 프로퍼티의 값이 null 이면 ?
	    	    				//---------------------------
	    	    				// set???(String)
	    	    				//---------------------------
	    	    				String setname = "set"+fldName;
			    				method = clz.getDeclaredMethod(setname, STRING_PARAM);
			    				if ( method != null ) {
			    					method.invoke(this, new String[]{val.trim()});
			    				}
	    					}
    					} else if ( method.getReturnType() == String[].class ) {
	    					String[] a = (String[])method.invoke(this);
	    					if ( a != null ) {
	    						for(int i=0; i < a.length; i++) {
	    							if ( a[i] != null ) a[i] = a[i].trim();
	    						}
	    						// 주소로 값을 변경했으므로, 값을 쓰기위한 invoke() 호출 필요 없음
	    					}
    					}
	    			}
    			} catch ( Throwable t ) {
    				//
    			}
    		}
        }
	}
}
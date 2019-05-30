/* Copyright (c) 2002-2011 by XMLVM.org
 *
 * Project Info:  http://www.xmlvm.org
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 */

#import "java_util_Collections.h"
#import "java_util_Arrays.h"
#import "java_util_List.h"
#import "java_util_ArrayList.h"

@implementation java_util_Collections

+ (java_util_List*) _GET_EMPTY_LIST
{
    const NSArray* empty_list = nil;
    if (empty_list==nil) {
        empty_list = [[NSArray alloc] init];
    }
    return (java_util_List*)[empty_list retain];
}

+ (java_util_Set*) _GET_EMPTY_SET
{
    const NSSet* empty_list = nil;
    if (empty_list==nil) {
        empty_list = [[NSSet alloc] init];
    }
    return (java_util_Set*)[empty_list retain];
}

+ (java_util_Map*) _GET_EMPTY_MAP
{
    const NSDictionary* empty_list = nil;
    if (empty_list==nil) {
        empty_list = [[NSDictionary alloc] init];
    }
    return (java_util_Map*)[empty_list retain];
}


//TODO Move the following to non-static [java_util_List toArray__]
+ (XMLVMArray*)toArray: (java_util_List*) list {
	XMLVMArray* a = [XMLVMArray createSingleDimensionWithType:0 andSize:[list size__]]; //Object array
	for (int i = 0; i < [list size__]; i++) {
		a->array.o[i] = [list get___int:i];
	}
	return a;
}

+ (void) sort___java_util_List: (java_util_List*) list {
	[java_util_Collections sort___java_util_List_java_util_Comparator:list:JAVA_NULL];
}

+ (void) sort___java_util_List_java_util_Comparator: (java_util_List*) list: (java_util_Comparator*) c {
	XMLVMArray* a = [java_util_Collections toArray:list];

	if (c == JAVA_NULL) {
		[java_util_Arrays sort___java_lang_Object_ARRAYTYPE:a];
	} else {
		[java_util_Arrays sort___java_lang_Object_ARRAYTYPE_java_util_Comparator:a:c];
	}
	for (int j = 0; j < [a count]; j++) {
		java_lang_Object* removedObj = [list set___int_java_lang_Object:j:a->array.o[j]];
		[removedObj release];
	}
}

+ (BOOL) addAll___java_util_Collection_java_lang_Object_ARRAYTYPE:(java_util_Collection*) c :(XMLVMArray*) elements
{
    BOOL result = false;
    for(int i = 0 ; i < elements->length ; i++)
        result |= [c add___java_lang_Object:[elements objectAtIndex:i]];
    return result;
}
@end
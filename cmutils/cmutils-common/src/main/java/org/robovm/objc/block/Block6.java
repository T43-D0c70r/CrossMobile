/*
 * Copyright (C) 2014 RoboVM AB
 *
 * Licensed under the Apache License, version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.robovm.objc.block;


/**
 * Block which takes 5 generic arguments and returns a generic value.
 */
public interface Block6<A, B, C, D, E, F, R> {

    /**
     * Invokes this block.
     *
     * @param a block argument number 1.
     * @param b block argument number 2.
     * @param c block argument number 3.
     * @param e block argument number 5.
     * @param f block argument number 6.
     * @return the block return value.
     */
    R invoke(A a, B b, C c, D d, E e, F f);
}
